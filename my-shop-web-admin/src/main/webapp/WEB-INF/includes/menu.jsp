<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<%@ page contentType="text/html; charset=gb2312" language="java" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${user.email}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
    </section>

    <!-- /.search form -->
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu" data-widget="tree">
        <li class="header">���ܲ˵�</li>
        <li class="active treeview">
            <a href="#">
                <i class="fa fa-users"></i> <span>�û�����</span>
                <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
            </a>
            <ul class="treeview-menu">
                <li><a href="/user/list"><i class="fa fa-circle-o"></i> �û��б�</a></li>
                <li><a href="/user/form"><i class="fa fa-circle-o"></i> �����û�</a></li>
            </ul>
        </li>
        <li class="active treeview">
            <a href="#">
                <i class="fa fa-book"></i> <span>���ݹ���</span>
                <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
            </a>
            <ul class="treeview-menu">
                <li><a href="/content/category/list"><i class="fa fa-circle-o"></i> ���ݷ���</a></li>
                <li><a href="/content/list"><i class="fa fa-circle-o"></i> �����б�</a></li>
            </ul>
        </li>
    </ul>
</aside>
