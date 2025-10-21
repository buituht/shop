<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-9">
      <h1><small>Danh sách bài viết</small></h1>
        <hr/>

        <a href="<c:url value='/admin/article/add'/>" class="btn btn-primary mb-3">
            <i class="fa fa-plus"></i> Thêm Bài Viết Mới
        </a>
        
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i> Danh Sách Bài Viết (Tổng: **${totalArticles}**)
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tiêu đề</th>
                                <th>Tác giả</th>
                                <th>Lượt xem</th>
                                <th>Trạng thái</th>
                                <th>Ngày tạo</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty articleList}">
                                    <c:forEach var="article" items="${articleList}">
                                        <tr>
                                            <td>${article.articleId}</td>
                                            <td><c:out value="${article.title}" /></td>
                                            <td>${article.authorName}</td>
                                            <td>${article.views}</td>
                                            <td>
                                                <span class="badge 
                                                    <c:choose>
                                                        <c:when test="${article.status eq 'Published'}">badge-success</c:when>
                                                        <c:when test="${article.status eq 'Draft'}">badge-warning</c:when>
                                                        <c:otherwise>badge-secondary</c:otherwise>
                                                    </c:choose>">
                                                    ${article.status}
                                                </span>
                                            </td>
                                            <td>${article.createdAt}</td>
                                            <td>
                                                <a href="<c:url value='/admin/article/edit?id=${article.articleId}'/>" class="btn btn-sm btn-warning">Sửa</a>
                                                <a href="<c:url value='/admin/article/delete?id=${article.articleId}'/>" 
                                                   onclick="return confirm('Bạn có chắc chắn muốn xóa bài viết ID ${article.articleId} này không?')"
                                                   class="btn btn-sm btn-danger">Xóa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="7" class="text-center">Không có bài viết nào được tìm thấy.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <c:if test="${totalPages > 1}">
                <div class="card-footer small text-muted">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                            <a class="page-link" href="<c:url value='/admin/article/list?page=${currentPage - 1}'/>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="<c:url value='/admin/article/list?page=${i}'/>">${i}</a>
                            </li>
                        </c:forEach>

                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                            <a class="page-link" href="<c:url value='/admin/article/list?page=${currentPage + 1}'/>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
