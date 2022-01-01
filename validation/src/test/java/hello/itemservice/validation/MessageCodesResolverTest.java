package hello.itemservice.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

//    MessageCodesResolver: 인터페이스, DefaultMessageCodesResolver: 구현체
//    DefaultMessageCodesResolver
//      -> 객체 오류: code + "." + object name,
//      -> 필드 오류: code + "." + object name + "." + field   /   code + "." + field   /   code + "." + field type   /   code
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for(String messageCode : messageCodes){
            //필드 에러
            assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
            );
        }
    }

}
