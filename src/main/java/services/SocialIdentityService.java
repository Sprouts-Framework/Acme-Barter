//package services;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import repositories.SocialIdentityRepository;
//import domain.SocialIdentity;
//import es.us.lsi.dp.services.AbstractService;
//import es.us.lsi.dp.services.contracts.ShowService;
//
//@Service
//@Transactional
//public class SocialIdentityService extends AbstractService<SocialIdentity, SocialIdentityRepository> implements ShowService<SocialIdentity>{
//
//	public Page<SocialIdentity> findSocialIdentitiesByUserId(Pageable page, int userId){
//		Page<SocialIdentity> result;
//		
//		result = repository.findSocialIdentityByUserId(userId, page);
//		Assert.notNull(result);
//		
//		return result;
//	}
//	
//}
