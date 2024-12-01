package database;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TacGia;

public class TacGiaDAO implements DAOInterface<TacGia> {
	private ArrayList<TacGia> data = new ArrayList<>();

	@Override
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> ketQua = new ArrayList<TacGia>();
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "SELECT * FROM tacgia";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maTacGia = rs.getString("matacgia");
				String hoVaTen = rs.getString("hovaten");
				Date ngaySinh = rs.getDate("ngaysinh");
				String tieuSu = rs.getString("tieusu");
				TacGia tacGia = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
				ketQua.add(tacGia);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ketQua;
	}

	@Override
	public TacGia selectById(TacGia t) {
		TacGia kq = null;
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "SELECT * FROM tacgia WHERE matacgia = ?";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTacGia());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maTacGia = rs.getString("matacgia");
				String hoVaTen = rs.getString("hovaten");
				Date ngaySinh = rs.getDate("ngaysinh");
				String tieuSu = rs.getString("tieusu");
				kq = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return kq;
	}

	@Override
	public int insert(TacGia t) {
		int kq = 0;
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "INSERT INTO tacgia(matacgia,hovaten,ngaysinh,tieusu) " + "VALUES(?,?,?,?)";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTacGia());
			pst.setString(2, t.getHoVaTen());
			pst.setDate(3, t.getNgaySinh());
			pst.setString(4, t.getTieuSu());
			kq = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem += this.insert(tacGia);
		}
		return dem;
	}

	@Override
	public int delete(TacGia t) {
		int kq = 0;
		try {
			// B1: tao ket noi
			Connection c = JDBCUtil.getConnection();

			// B3: cau lenh insert sql
			String sql = "DELETE FROM tacgia "

					+ "WHERE matacgia = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTacGia());
			kq = pst.executeUpdate();

			// B5: close connection
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem += this.delete(tacGia);
		}
		return dem;
	}

	@Override
	public int update(TacGia t) {
		int kq = 0;
		try {
			// B1: tao ket noi
			Connection c = JDBCUtil.getConnection();

			// B3: cau lenh insert sql
			String sql = "UPDATE tacgia " + "SET hovaten = ?,ngaysinh = ?,tieusu=? "

					+ "WHERE matacgia = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getMaTacGia());
			kq = pst.executeUpdate();

			// B5: close connection
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kq;
	}
}
