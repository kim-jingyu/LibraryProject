package example.template;

import example.annotation.MyTransaction;
import example.dao.BookDAO;
import example.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@RequiredArgsConstructor
public abstract class TransactionTemplate {
    private final SqlSessionFactory sqlSessionFactory;

    public final ObservableList<BookVO> transaction() {
        SqlSession session = sqlSessionFactory.openSession();
        ObservableList<BookVO> data = FXCollections.observableArrayList();
        try {
            BookDAO dao = new BookDAO(session);
            call(data, dao);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            printEx();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return data;
    }

    @MyTransaction
    protected abstract void call(ObservableList<BookVO> data, BookDAO dao);
    protected abstract void printEx();
}
