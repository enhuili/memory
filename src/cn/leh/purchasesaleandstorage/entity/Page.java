package cn.leh.purchasesaleandstorage.entity;

import java.sql.ResultSet;

import com.hospital.Dao.AdvDao;

public class Page extends AdvDao {
	/**
	 * 鑾峰彇閫氱敤椤甸泦鍚�
	 * 
	 * @param tablename
	 *            琛ㄥ悕
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @return 闆嗗悎
	 */
	public ResultSet getPagers(String tablename, int pagesize, int currpage) {
		return getrs("select top(" + pagesize + ")* from " + tablename + " where id not in " + "(select top((" + currpage + "-1)*" + pagesize + ") id from " + tablename + " order by id)" + " order by id", new Object[] {});
	}

	/**
	 * 鑾峰彇閫氱敤椤甸泦鍚�
	 * 
	 * @param tablename
	 *            琛ㄥ悕
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @param where
	 *            鍙傛暟 渚嬪id=1
	 * @return 闆嗗悎
	 */
	public ResultSet getonePagers(String tablename, int pagesize, int currpage, String where) {
		return getrs("select top(" + pagesize + ")* from " + tablename + " where " + where + " and id not in " + "(select top((" + currpage + "-1)*" + pagesize + ") id from " + tablename + " where " + where + " order by id)" + " order by id", new Object[] {});
	}

	/**
	 * 鑾峰彇閫氱敤椤甸泦鍚�
	 * 
	 * @param tablename
	 *            琛ㄥ悕
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @param where
	 *            鍙傛暟 渚嬪id=1
	 * @return 闆嗗悎
	 */
	public ResultSet getonePagers(String tablename, int pagesize, int currpage, String where, String order) {
		String sql = "select top(" + pagesize + ")* from " + tablename + " where " + where + " and id not in " + "(select top((" + currpage + "-1)*" + pagesize + ") id from " + tablename + " where " + where + " order by " + order + ")" + " order by " + order;
		return getrs(sql, new Object[] {});
	}

	// 浠撳簱淇℃伅绠＄悊鐨勫琛ㄦ煡璇�
	public ResultSet getStorageUsers(int pagesize, int currpage) {
		return getrs("select s.id,s.name,s.info,s.userid,u.id,u.name,u.gender,u.age,u.typeid,u.info,u.email,u.phone,u.discount from storage s, users u where s.userid=u.id", new Object[] {});
	}

	// 杩涜揣淇℃伅鐨勫琛ㄦ煡璇�
	public ResultSet getIteminTwo(String s, int pagesize, int currpage) {
		if (s == null) {
			return getrs("select i.id,i.ITEMID,i.STORAGEID,i.count,i.TIME,i.PRICE,i.totalprice,d.id,d.name,s.ID,s.name from ITEMIN i, ITEMdata d, STORAGE s where i.ITEMID=d.id and i.STORAGEID=s.ID", new Object[] {});
		} else {
			return getrs("select i.id,i.ITEMID,i.STORAGEID,i.count,i.TIME,i.PRICE,i.totalprice,d.id,d.name,s.ID,s.name from ITEMIN i, ITEMdata d, STORAGE s where i.ITEMID=d.id and i.STORAGEID=s.ID" + s, new Object[] {});
		}
	}

	// 鍑鸿揣淇℃伅鐨勫琛ㄦ煡璇�
	public ResultSet getItemoutTwo(String s, int pagesize, int currpage) {
		if (s == null) {
			return getrs("select i.id,i.ITEMID,i.STORAGEID,i.count,i.TIME,d.id,d.name,s.ID,s.name from ITEMOUT i, ITEMdata d, STORAGE s where i.ITEMID=d.id and i.STORAGEID=s.ID", new Object[] {});
		} else {
			return getrs("select i.id,i.ITEMID,i.STORAGEID,i.count,i.TIME,d.id,d.name,s.ID,s.name from ITEMOUT i, ITEMdata d, STORAGE s where i.ITEMID=d.id and i.STORAGEID=s.ID" + s, new Object[] {});
		}
	}

	/**
	 * 鑾峰彇閫氱敤澶氳〃杩炴帴鏌ヨ鐨勭粨鏋滈泦
	 * 
	 * @param tables
	 *            涓�缇よ〃鍚� 渚嬪:user u,usertype ut
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @param where
	 *            鏉′欢璇彞 渚嬪:u.tid = ut.id 鎴� u.tid = ut.id and u.id = 1
	 * 
	 * @娉ㄦ剰 鍒嗛〉椤哄簭灏嗕細鏍规嵁绗竴涓猧d(u.tid)涓洪『搴�
	 * @return 缁撴灉闆�
	 */

	public ResultSet getPagers(String tables, int pagesize, int currpage, String where) {
		String id = where.substring(0, where.indexOf('=')); // 鑾峰彇瑕佹牴鎹粈涔坕d杩涜鍒嗛〉鎺掑簭,榛樿涓烘潯浠惰鍙ョ殑绗竴涓猧d
		String sql;
		if (where.indexOf("order") > -1) {
			sql = "select top(" + pagesize + ")* from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + ")" + " and " + where;

		} else {
			sql = "select top(" + pagesize + ")* from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + " order by " + id + ")" + " and " + where + " order by " + id;
		}
		return getrs(sql, new Object[] {});
	}

	/**
	 * 鑾峰彇閫氱敤澶氳〃杩炴帴鏌ヨ鐨勭粨鏋滈泦
	 * 
	 * @param tables
	 *            涓�缇よ〃鍚� 渚嬪:user u,usertype ut
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @param where
	 *            鏉′欢璇彞 渚嬪:u.tid = ut.id 鎴� u.tid = ut.id and u.id = 1
	 * @return
	 */
	public ResultSet getPagers(String tables, int pagesize, int currpage, String where, String id) {
		String sql;
		if (where.indexOf("order") > -1) {
			sql = "select top(" + pagesize + ")* from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + ")" + " and " + where;

		} else {
			sql = "select top(" + pagesize + ")* from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + " order by " + id + ")" + " and " + where + " order by " + id;
		}
		return getrs(sql, new Object[] {});
	}

	public int getPageCount(String tables, String where) {
		String sql = "select count(*) from " + tables + " where  " + where;
		return getid(sql, O());
	}

	/**
	 * 鑾峰彇閫氱敤澶氳〃杩炴帴鏌ヨ鐨勭粨鏋滈泦
	 * 
	 * @param params
	 *            鍙傛暟鍒楄〃 渚嬪:u.id,u.name
	 * @param tables
	 *            涓�缇よ〃鍚� 渚嬪:user u,usertype ut
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @param where
	 *            鏉′欢璇彞 渚嬪:u.tid = ut.id 鎴� u.tid = ut.id and u.id = 1
	 * @return
	 */
	public ResultSet getPagers(String params, String tables, int pagesize, int currpage, String where) {
		String id = where.substring(0, where.indexOf('=')); // 鑾峰彇瑕佹牴鎹粈涔坕d杩涜鍒嗛〉鎺掑簭,榛樿涓烘潯浠惰鍙ョ殑绗竴涓猧d
		String sql;
		if (where.indexOf("order") > -1) {
			sql = "select top(" + pagesize + ") " + params + " from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + ")" + " and " + where;
		} else {
			sql = "select top(" + pagesize + ") " + params + " from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + " order by " + id + ")" + " and " + where + " order by " + id;
		}
		return getrs(sql, O());
	}

	/**
	 * 鑾峰彇閫氱敤澶氳〃杩炴帴鏌ヨ鐨勭粨鏋滈泦
	 * 
	 * @param params
	 *            鍙傛暟鍒楄〃 渚嬪:u.id,u.name
	 * @param tables
	 *            涓�缇よ〃鍚� 渚嬪:user u,usertype ut
	 * @param pagesize
	 *            椤靛ぇ灏�
	 * @param currpage
	 *            褰撳墠椤�
	 * @param where
	 *            鏉′欢璇彞 渚嬪:u.tid = ut.id 鎴� u.tid = ut.id and u.id = 1
	 * @param id
	 *            鏍规嵁浠�涔堣繘琛屽唴杩炴帴 渚嬪i.id
	 * @return
	 */
	public ResultSet getPagers(String params, String tables, int pagesize, int currpage, String where, String id) {
		String sql;
		if (where.indexOf("order") > -1) {
			sql = "select top(" + pagesize + ") " + params + " from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + ")" + " and " + where;
		} else {
			sql = "select top(" + pagesize + ") " + params + " from " + tables + " where " + id + " not in " + "(select top((" + currpage + "-1)*" + pagesize + ") " + id + " from " + tables + " where " + where + " order by " + id + ")" + " and " + where + " order by " + id;
		}
		return getrs(sql, O());
	}
}
