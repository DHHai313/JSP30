package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TacGia;
import model.TheLoai;

public class TheLoaiDAO implements DAOInterface<TheLoai> {
	

	@Override
	public ArrayList<TheLoai> selectAll() {
		ArrayList<TheLoai> ketQua = new ArrayList<TheLoai>();
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "SELECT * FROM theloai";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maTheLoai = rs.getString("matheloai");
				String tenTheLoai = rs.getString("tentheloai");
				
				TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
				ketQua.add(theLoai);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ketQua;
	}

	@Override
	public TheLoai selectById(TheLoai t) {
		TheLoai kq = null;
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "SELECT * FROM theloai WHERE matheloai = ?";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTheLoai());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maTheLoai = rs.getString("matheloai");
				String tenTheLoai = rs.getString("tentheloai");
				kq = new TheLoai(maTheLoai, tenTheLoai);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}

	@Override
	public int insert(TheLoai t) {
		int kq = 0;
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "INSERT INTO theloai(matheloai,tentheloai) " + "VALUES(?,?)";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTheLoai());
			pst.setString(2, t.getTenTheLoai());

			kq = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<TheLoai> arr) {
		int dem = 0;
		for (TheLoai TheLoai : arr) {
			dem += this.insert(TheLoai);
		}
		return dem;
	}

	@Override
	public int delete(TheLoai t) {
		int kq = 0;
		try {
			// B1: tao ket noi
			Connection c = JDBCUtil.getConnection();

			// B3: cau lenh insert sql
			String sql = "DELETE FROM theloai "

					+ "WHERE matheloai = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTheLoai());
			kq = pst.executeUpdate();

			// B5: close connection
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<TheLoai> arr) {
		int dem = 0;
		for (TheLoai TheLoai : arr) {
			dem += this.delete(TheLoai);
		}
		return dem;
	}

	@Override
	public int update(TheLoai t) {
		int kq = 0;
		try {
			// B1: tao ket noi
			Connection c = JDBCUtil.getConnection();

			// B3: cau lenh insert sql
			String sql = "UPDATE theloai " + "SET tentheloai = ? "

					+ "WHERE matheloai = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTheLoai());
			kq = pst.executeUpdate();

			// B5: close connection
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}
}
