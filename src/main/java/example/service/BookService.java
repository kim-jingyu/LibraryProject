package example.service;

import example.annotation.MyTransaction;
import example.dao.BookDAO;
import example.dto.BookUpdateDTO;
import example.template.Transaction;
import example.vo.BookVO;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;

import static example.exception.ExceptionHandler.*;

@RequiredArgsConstructor
public class BookService {
    private final SqlSessionFactory sqlSessionFactory;

    public ObservableList<BookVO> getAllBook() {
        return new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                data.addAll(dao.selectAllBookVO());
            }

            @Override
            protected void printEx() {
                System.err.println(SEARCH_BOOKS_EX);
            }
        }.transaction();
    }

    @MyTransaction
    public ObservableList<BookVO> searchBookByISBN(String bisbn) {
        return new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                data.add(dao.selectByISBNBookVO(bisbn));
            }

            @Override
            protected void printEx() {
                System.err.println(SEARCH_ONE_BOOK_EX);
            }
        }.transaction();
    }

    public ObservableList<BookVO> searchBooksByKeyword(String keyword) {
        return new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                data.addAll(dao.selectByTitleBookVO(keyword));
            }

            @Override
            protected void printEx() {
                System.err.println(SEARCH_BY_BOOK_TITLE_EX);
            }
        }.transaction();
    }

    public void updateBookTitle(String bisbn, String bttile) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updateTitleByISBN(BookUpdateDTO.changeTitle(bisbn, bttile));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPDATE_BOOK_TITLE_EX);
            }
        }.transaction();
    }

    public void updateBookPrice(String bisbn, Integer bprice) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updatePriceByISBN(BookUpdateDTO.changePrice(bisbn, bprice));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPDATE_BOOK_PRICE_EX);
            }
        }.transaction();
    }

    public void updateBookAuthor(String bisbn, String bauthor) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updateAuthorByISBN(BookUpdateDTO.changeAuthor(bisbn, bauthor));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPDATE_AUTHOR_EX);
            }
        }.transaction();
    }

    public void updateBookTitleAndAuthor(String bisbn, String btitle, String bauthor) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updateTitleAndAuthorByISBN(BookUpdateDTO.changeTitleAndAuthor(bisbn, btitle, bauthor));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPDATE_TITLE_AND_AUTHOR_EX);
            }
        }.transaction();
    }

    public void updateBookTitleAndPrice(String bisbn, String btitle, Integer bprice) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updateTitleAndPriceByISBN(BookUpdateDTO.changeTitleAndPrice(bisbn, btitle, bprice));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPDATE_TITLE_AND_PRICE_EX);
            }
        }.transaction();
    }

    public void updateBookPriceAndAuthor(String bisbn, Integer bprice, String bauthor) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updatePriceAndAuthorByISBN(BookUpdateDTO.changePriceAndAuthor(bisbn, bprice, bauthor));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPDATE_PRICE_AND_AUTHOR_EX);
            }
        }.transaction();
    }

    public void updateAll(String bisbn, String btitle, Integer bprice, String bauthor) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.updateAll(BookUpdateDTO.changeAll(bisbn, btitle, bprice, bauthor));
                System.out.println("업데이트 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(UPATE_TITLE_PRICE_AUTHOR_EX);
            }
        }.transaction();
    }

    public void deleteBookByISBN(String isbn) {
        new Transaction(sqlSessionFactory) {
            @Override
            protected void call(ObservableList<BookVO> data, BookDAO dao) {
                int result = dao.deleteByISBN(isbn);
                System.out.println("삭제 완료 수 : " + result);
            }

            @Override
            protected void printEx() {
                System.err.println(DELETE_BOOK_EX);
            }
        }.transaction();
    }
}
