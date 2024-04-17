package example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateDTO {
    private String bisbn;
    private String btitle;
    private int bprice;
    private String bauthor;

    public static BookUpdateDTO changeTitle(String bisbn, String btitle) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .btitle(btitle)
                .build();
    }

    public static BookUpdateDTO changePrice(String bisbn, Integer bprice) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .bprice(bprice)
                .build();
    }

    public static BookUpdateDTO changeAuthor(String bisbn, String bauthor) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .bauthor(bauthor)
                .build();
    }

    public static BookUpdateDTO changeTitleAndPrice(String bisbn, String btitle, Integer bprice) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .btitle(btitle)
                .bprice(bprice)
                .build();
    }

    public static BookUpdateDTO changeTitleAndAuthor(String bisbn, String btitle, String bauthor) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .btitle(btitle)
                .bauthor(bauthor)
                .build();
    }

    public static BookUpdateDTO changePriceAndAuthor(String bisbn, Integer bprice, String bauthor) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .bprice(bprice)
                .bauthor(bauthor)
                .build();
    }

    public static BookUpdateDTO changeAll(String bisbn, String btitle, Integer bprice, String bauthor) {
        return BookUpdateDTO.builder()
                .bisbn(bisbn)
                .btitle(btitle)
                .bprice(bprice)
                .bauthor(bauthor)
                .build();
    }
}
