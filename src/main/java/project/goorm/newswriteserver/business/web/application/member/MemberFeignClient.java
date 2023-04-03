package project.goorm.newswriteserver.business.web.application.member;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "authentication-server", name = "authentication-server")
public interface MemberFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/member/exist/{memberId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean memberExistsByMemberId(@PathVariable("memberId") Long memberId);
}
