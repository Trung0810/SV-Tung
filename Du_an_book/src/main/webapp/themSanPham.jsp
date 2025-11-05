<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
    <h3 class="text-center mb-3">Thêm sản phẩm mới</h3>
    <form action="themSanPham" method="post">
        <div class="mb-3">
            <label class="form-label">Tên sản phẩm:</label>
            <input type="text" name="tenSP" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Giá:</label>
            <input type="number" step="0.01" name="gia" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Ảnh (URL):</label>
            <input type="text" name="anh" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Mô tả:</label>
            <textarea name="moTa" class="form-control"></textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">Mã thể loại:</label>
            <input type="number" name="maTL" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Mã tác giả:</label>
            <input type="number" name="maTG" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Thêm</button>
        <a href="quanLySanPham.jsp" class="btn btn-secondary">Quay lại</a>
    </form>
</body>
</html>
