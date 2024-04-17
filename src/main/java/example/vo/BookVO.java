package example.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookVO {
    private String bisbn;
    private String btitle;
    private int bprice;
    private String bauthor;

    public static BookVO of(String bisbn, String btitle) {
        return BookVO.builder()
                .bisbn(bisbn)
                .btitle(btitle)
                .build();
    }
}
