package example.service;

import example.dao.BookDAO;
import example.dto.BookUpdateDTO;
import example.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import static example.exception.ExceptionHandler.*;

@RequiredArgsConstructor
public class BookService {
    private final SqlSessionFactory sqlSessionFactory;

    public ObservableList<BookVO> getAllBook() {
        SqlSession session = sqlSessionFactory.openSession();
        ObservableList<BookVO> data = FXCollections.observableArrayList();
        try {
            BookDAO dao = new BookDAO(session);
            data.addAll(dao.selectAllBookVO());
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(SEARCH_BOOKS_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return data;
    }

    public ObservableList<BookVO> searchBookByISBN(String bisbn) {
        SqlSession session = sqlSessionFactory.openSession();
        ObservableList<BookVO> data = FXCollections.observableArrayList();
        try {
            BookDAO dao = new BookDAO(session);
            data.add(dao.selectByISBNBookVO(bisbn));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(SEARCH_ONE_BOOK_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return data;
    }

    public ObservableList<BookVO> searchBooksByKeyword(String keyword) {
        SqlSession session = sqlSessionFactory.openSession();
        ObservableList<BookVO> data = FXCollections.observableArrayList();
        try {
            BookDAO dao = new BookDAO(session);
            data.addAll(dao.selectByTitleBookVO(keyword));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(SEARCH_BY_BOOK_TITLE_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return data;
    }

    public void updateBookTitle(String bisbn, String bttile) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updateTitleByISBN(BookUpdateDTO.changeTitle(bisbn, bttile));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPDATE_BOOK_TITLE_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBookPrice(String bisbn, Integer bprice) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updatePriceByISBN(BookUpdateDTO.changePrice(bisbn, bprice));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPDATE_BOOK_PRICE_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBookAuthor(String bisbn, String bauthor) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updateAuthorByISBN(BookUpdateDTO.changeAuthor(bisbn, bauthor));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPDATE_AUTHOR_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBookTitleAndAuthor(String bisbn, String btitle, String bauthor) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updateTitleAndAuthorByISBN(BookUpdateDTO.changeTitleAndAuthor(bisbn, btitle, bauthor));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPDATE_TITLE_AND_AUTHOR_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBookTitleAndPrice(String bisbn, String btitle, Integer bprice) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updateTitleAndPriceByISBN(BookUpdateDTO.changeTitleAndPrice(bisbn, btitle, bprice));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPDATE_TITLE_AND_PRICE_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBookPriceAndAuthor(String bisbn, Integer bprice, String bauthor) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updatePriceAndAuthorByISBN(BookUpdateDTO.changePriceAndAuthor(bisbn, bprice, bauthor));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPDATE_PRICE_AND_AUTHOR_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateAll(String bisbn, String btitle, Integer bprice, String bauthor) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.updateAll(BookUpdateDTO.changeAll(bisbn, btitle, bprice, bauthor));
            System.out.println("업데이트 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(UPATE_TITLE_PRICE_AUTHOR_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteBookByISBN(String isbn) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookDAO dao = new BookDAO(session);
            int result = dao.deleteByISBN(isbn);
            System.out.println("삭제 완료 수 : " + result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.err.println(DELETE_BOOK_EX);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
