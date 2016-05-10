package controllers.user.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.common.AbstractPostController;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;

@Controller("followUserController")
@RequestMapping("user/{context}/follow")
public class FollowUserController extends AbstractPostController<UserService> implements AddsToModel {

	@Override
	protected void action(List<String> context) {
		Integer userId = new Integer(context.get(0));
		Assert.notNull(userId);
		service.followOrUnfollow(userId);
	}

	@Override
	protected String onSuccess() {
		return "/user/user/following/list.do";
	}

	@Override
	protected String view() {
		return "user/follow-unfollow";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer userId = new Integer(context.get(0));
		Assert.notNull(userId);
		User toFollow = service.findById(userId);

		boolean isFollowing = service.isFollowing(userId);

		objects.put("isFollowing", isFollowing);
		objects.put("toFollow", toFollow);
	}

	@Override
	protected String successCode() {
		return "user.follow-unfollow.success";
	}
	

}
