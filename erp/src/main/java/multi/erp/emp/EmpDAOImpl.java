package multi.erp.emp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("empDao")
public class EmpDAOImpl implements EmpDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public MemberVO login(MemberVO loginUser) {
		return sqlSession.selectOne("multi.erp.emp.login", loginUser);
	}
	
	@Override
	public ArrayList<MemberVO> getTreeEmpList(String deptno) {
		return null;
	}

	@Override
	public int insert(MemberVO user) {
		return 0;
	}

	@Override
	public ArrayList<MemberVO> getMemberList() {
		return null;
	}

	@Override
	public int delete(String id) {
		return 0;
	}

	@Override
	public MemberVO read(String id) {
		return null;
	}

	@Override
	public ArrayList<MemberVO> search(String column, String search, String pass) {
		return null;
	}

	@Override
	public int update(MemberVO user) {
		return 0;
	}

	@Override
	public boolean idCheck(String id) {
		return false;
	}

	@Override
	public MemberVO findById(String id) {
		return null;
	}

}
