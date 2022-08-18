package jphwany.soloproject.member;

import com.google.gson.Gson;
import jphwany.soloproject.api.v1.member.controller.MemberController;
import jphwany.soloproject.api.v1.member.dto.MemberResponseDto;
import jphwany.soloproject.api.v1.member.entity.Member;
import jphwany.soloproject.api.v1.member.mapper.MemberMapper;
import jphwany.soloproject.api.v1.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.isArray;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    Member member1;
    Member member2;

    MemberResponseDto dto1;
    MemberResponseDto dto2;


    @BeforeEach
    void set(){
        member1 = new Member(1L, "김코딩", "s4goodbye", Member.Gender.Male, "프로젝트스테이츠", "005", "001");
        member2 = new Member(2L, "jp", "dfladfAT", Member.Gender.Female, "드넓은I세상", "007", "002");

        dto1 = new MemberResponseDto(1L, "김코딩", Member.Gender.Male, "프로젝트스테이츠", "005", "001");
        dto2 = new MemberResponseDto(2L, "jp", Member.Gender.Female, "드넓은I세상", "007", "002");
    }

    @Test
    public void getCompanyFilterTest() throws Exception {

        //given
        List<Member> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);

        List<MemberResponseDto> resDto = new ArrayList<>();
        resDto.add(dto1);
        resDto.add(dto2);

        given(memberService.findByCompanyType(Mockito.anyString())).willReturn(list);
        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(resDto);

        //when
        ResultActions actions = mockMvc.perform(get("/v1/members/filter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

//    @Test
//    public void getCompanyLocationTest() throws Exception{
//        //given
//        List<Member> memberList = List.of(member1);
//        List<MemberResponseDto> memberResponseDto = List.of(dto1);
//
//        String companyLocation = member1.getCompanyLocation();
//
//        given(memberService.findByCompanyLocation(Mockito.anyString())).willReturn(memberList);
//        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(memberResponseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(get("/v1/members/{company-location}", companyLocation)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray());
//
//    }

    @Test
    public void getMembersTest() throws Exception{
        //given
        int page = 1;
        int size = 10;

        List<Member> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Member> pageMembers = new PageImpl<>(list, pageable, list.size());

        List<MemberResponseDto> respoDto = new ArrayList<>();
        respoDto.add(dto1);
        respoDto.add(dto2);


        given(memberService.findByMembers(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageMembers);
        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(respoDto);



    }

}
