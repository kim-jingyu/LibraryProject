package example.dao;

import example.dto.BookUpdateDTO;
import example.vo.BookVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@RequiredArgsConstructor
public class BookDAO {
    private final SqlSession session;

    public List<BookVO> selectAllBookVO() {
        return session.selectList("com.book.selectAllBookVO");
    }

    public BookVO selectByISBNBookVO(String bisbn) {
        return session.selectOne("com.book.selectByISBNBookVO", bisbn);
    }

    public List<BookVO> selectByTitleBookVO(String keyword) {
        return session.selectList("com.book.selectByTitleBookVO", keyword);
    }

    public int updateTitleByISBN(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updateTitleByISBN", bookUpdateDTO);
    }

    public int updatePriceByISBN(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updatePriceByISBN", bookUpdateDTO);
    }

    public int updateAuthorByISBN(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updateAuthorByISBN", bookUpdateDTO);
    }

    public int updateTitleAndAuthorByISBN(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updateTitleAndAuthorByISBN", bookUpdateDTO);
    }

    public int updateTitleAndPriceByISBN(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updateTitleAndPriceByISBN", bookUpdateDTO);
    }

    public int updatePriceAndAuthorByISBN(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updatePriceAndAuthorByISBN", bookUpdateDTO);
    }

    public int updateAll(BookUpdateDTO bookUpdateDTO) {
        return session.update("com.book.updateAll", bookUpdateDTO);
    }

    public int deleteByISBN(String bisbn) {
        return session.delete("com.book.deleteByISBN", bisbn);
    }
}
