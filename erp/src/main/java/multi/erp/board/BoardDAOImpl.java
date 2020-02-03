package multi.erp.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//Mybatis를 이용해서 DB연동을 하기 위한 DAO클래스
//sql문 하나당 메소드 한 개를 정의.
@Repository("boardDao")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<BoardVO> boardList() {
		//select문의 실행결과가 여러 개의 레코드를 반환하는 경우 사용
		//sqlSession.selectList("mapper의 등록한 sql문 id(namespace포함)");
		return sqlSession.selectList("multi.erp.board.listall");
	}

	@Override
	public int insert(BoardVO board) {
		return sqlSession.insert("multi.erp.board.insert", board);
	}

	@Override
	public List<BoardVO> categorySearch(String category) {
		System.out.println("dao=>"+category);
		List<BoardVO> list = sqlSession.selectList("multi.erp.board.categorySearch", category);
		System.out.println("dao=>"+list.size());
		return sqlSession.selectList("multi.erp.board.categorySearch", category);
	}

	//검색어별로 조회 - 동적 SQL 활용
	@Override
	public List<BoardVO> searchList(String tag, String search) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag", tag);
		map.put("search", search);
		return sqlSession.selectList("multi.erp.board.dynamicSearch", map);
	}

	@Override
	public List<BoardVO> pageList() {
		return null;
	}

	@Override
	public BoardVO read(String board_no) {
		return null;
	}

	@Override
	public int update(BoardVO board) {
		return 0;
	}

	@Override
	public int delete(String board_no) {
		return 0;
	}

}
