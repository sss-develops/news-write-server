package project.goorm.newswriteserver.business.web.application.member;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("authentication-server")
public interface MemberFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/member/exist/{memberId}", consumes = "application/json")
    ResponseEntity<Boolean> memberExistsByMemberId(@RequestParam("memberId") Long memberId);
}
