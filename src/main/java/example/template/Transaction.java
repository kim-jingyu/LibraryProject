package example.template;

import example.dao.BookDAO;
import example.vo.BookVO;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSessionFactory;

public class Transaction extends TransactionTemplate{

    public Transaction(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    protected void call(ObservableList<BookVO> data, BookDAO dao) {
        data.addAll(dao.selectAllBookVO());
    }

    @Override
    protected void printEx() {
    }
}
