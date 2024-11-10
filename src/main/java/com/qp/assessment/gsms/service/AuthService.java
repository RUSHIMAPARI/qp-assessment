package com.qp.assessment.gsms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qp.assessment.gsms.entity.RoleTypes;
import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.exception.ExchangeException;
import com.qp.assessment.gsms.exception.FieldValidationException;
import com.qp.assessment.gsms.exception.ResourceNotFoundException;
import com.qp.assessment.gsms.repository.UsersRepository;
import com.qp.assessment.gsms.request.AbstractRequest;
import com.qp.assessment.gsms.request.UserRequest;
import com.qp.assessment.gsms.response.AbstractResponse;
import com.qp.assessment.gsms.response.UserPageWrapperResponse;
import com.qp.assessment.gsms.response.UserResponseModel;
import com.qp.assessment.gsms.util.CommonConstants.ErrorConstants;

@Service
public class AuthService implements ICrudService<User, Long> {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PersistenceContext
	private EntityManager entityManager;

	public User findByUserName(String userName) {
		return userRepository.findByUserNameAndIsActive(userName, 'Y').orElseThrow(
				() -> new ResourceNotFoundException(ErrorConstants.RESOURCE_FOR_NOT_FOUND, userName, "User"));
	}

	public void savePassword(Long applicationKey, String password) {
		User user = userRepository.findById(applicationKey).get();
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}

	public User findByUserKey(Long userid) {
		return userRepository.findById(userid).get();
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void update(User user) {
		
	}

	@Override
	public Optional<User> findByKey(Long key) {
		return userRepository.findByKeyAndIsActive(key, 'Y');
	}

	@Override
	public Page<AbstractResponse> findAllFilteredPageable(Pageable pageable, Optional<AbstractRequest> filterText,
			Long userId) {
		return null;
	}

	public UserPageWrapperResponse findAllUsersFilteredPageable(Pageable pageable, Optional<String> filterText, User user) {
		Specification<User> spec = null;
		if(filterText.isPresent()) {
			spec = allFilteredSpec(filterText);
		}
		
    	Page<UserResponseModel> page = userRepository.findAll(spec, pageable)
		.map(new Function<User, UserResponseModel>() {
		    @Override
		    public UserResponseModel apply(User user) {
		    	return UserResponseModel.init(user);
		    }
		});
    	
		return UserPageWrapperResponse.init(page);
	}
	
	private static Specification<User> allFilteredSpec(Optional<String> filterText) {
		return new Specification<User>() {
			private static final long serialVersionUID = 1L;
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<>();
				
				predicates.add(builder.like(root.get("userName"), "%"+filterText.get().trim()+"%"));
				predicates.add(builder.like(root.get("userName"), "%"+filterText.get().trim().toUpperCase()+"%"));
				
                return builder.or(predicates.toArray(new Predicate[] {}));
            }
        };
	}

	@Transactional
	public void saveUser(UserRequest userRequest) {
//		if (userExistsByUserName(null, userRequest.getUserName())) {
//			throw new FieldValidationException(ErrorConstants.RESOURCE_ALREADY_EXISTS, userRequest.getUserName());
//		}

		User user = userRequest.mapToEntity();
		
		// TODO
		user.setPassword(passwordEncoder.encode("Admin@123"));
		user.setRole(RoleTypes.valueOf(userRequest.getRole()));
		try {
			save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExchangeException(e.getMessage());
		}
	}

	private boolean userExistsByUserName(Long key, String userName) {
		User user = findByUserName(userName);
		if (user != null) {
			if (key == null || (key != null && !key.equals(user.getKey()))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// TODO
	public void updateUser(Long key, UserRequest userRequest) {
		if (userExistsByUserName(key, userRequest.getUserName())) {
			throw new FieldValidationException(ErrorConstants.RESOURCE_ALREADY_EXISTS, userRequest.getUserName());
		}

		User user = findByKey(key)
				.orElseThrow(() -> new ResourceNotFoundException("User not found", userRequest.getUserName(), "User"));
		userRequest.updateEntity(user);

		try {
			save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExchangeException(e.getMessage());
		}
	}


}
