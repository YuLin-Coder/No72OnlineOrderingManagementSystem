<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
 <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>菜单</a>
                    <ul class="sub-menu">
                        <li><a href="${ctx}/itemCategory/findBySql"><i class="icon-font">&#xe008;</i>类目管理</a></li>
                        <li><a href="${ctx}/user/findBySql"><i class="icon-font">&#xe008;</i>用户管理</a></li>
                        <li><a href="${ctx}/item/findBySql"><i class="icon-font">&#xe005;</i>菜品管理</a></li>
                        <li><a href="${ctx}/itemOrder/findBySql"><i class="icon-font">&#xe006;</i>订单管理</a></li>
                        
                       
                          <li><a href="${ctx}/news/findByObj"><i class="icon-font">&#xe006;</i>公告管理</a></li>
                           <li><a href="${ctx}/message/findByObj"><i class="icon-font">&#xe006;</i>售后管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>