package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SanPham;
import model.TacGia;
import model.TheLoai;


public class SanPhamDAO implements DAOInterface<SanPham>{
	private ArrayList<SanPham> data = new ArrayList<>();
	
	@Override
	public ArrayList<SanPham> selectAll() {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
		try {
			// tao ket noi
			Connection c = JDBCUtil.getConnection();
			// cau lenh sql
			String sql = "SELECT * FROM sanpham";
			// tao prepare statement
			PreparedStatement pst = c.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				String maTacGia = rs.getString("matacgia");
				int namXuatBan = rs.getInt("namxuatban");
				double giaNhap = rs.getDouble("gianhap");
				double giaBan = rs.getDouble("giaBan");
				double giaGoc = rs.getDouble("giaGoc");
				double soLuong = rs.getDouble("soLuong");
				String maTheLoai = rs.getString("matheloai");
				String ngonNgu = rs.getString("ngonngu");
				String moTa = rs.getString("mota");
				// tao khoa ngoai
				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(maTacGia, "", null, "")));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ketQua;
	}

	@Override
	public SanPham selectById(SanPham t) {
		for (SanPham SanPham : data) {
			if(data.equals(t)) {
				return SanPham;
			}
		}
		return null;
	}

	@Override
	public int insert(SanPham t) {
		if (this.selectById(t)==null) {
			this.data.add(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<SanPham> arr) {
		int dem = 0;
		for (SanPham SanPham : arr) {
			dem+=this.insert(SanPham);
		}
		return dem;
	}

	@Override
	public int delete(SanPham t) {
		if (this.selectById(t)!=null) {
			this.data.remove(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<SanPham> arr) {
		int dem = 0;
		for (SanPham SanPham : arr) {
			dem+=this.delete(SanPham);
		}
		return dem;
	}

	@Override
	public int update(SanPham t) {
		if (this.selectById(t)!=null) {
			this.data.remove(t);
			this.data.add(t);
			return 1;
		}
		return 0;
	}
}

