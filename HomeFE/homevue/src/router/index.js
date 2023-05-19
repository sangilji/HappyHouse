import Vue from "vue";
import VueRouter from "vue-router";
import HomeMainView from "../views/HomeMainView.vue";
import LoginView from "@/views/user/LoginView.vue";
import JoinView from "@/views/user/JoinView.vue";
import BoardView from "@/views/board/AppBoard.vue";
import BoardWriteView from "@/views/board/BoardWriteView.vue";
import mapView from "@/views/map/MapView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: HomeMainView,
  },
  {
    path:"",
    name: "main",
    component: HomeMainView,
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/join",
    name: "join",
    component: JoinView,
  },
  {
    path: "/map",
    name: "map",
    component: mapView,
  },
  {
    path: "/list",
    name: "boardlist",
    component: BoardView,
  },
  {
    path: "/boardwrite",
    name: "boardwrite",
    // beforeEnter: onlyAuthUser,
    component:BoardWriteView,
  },
  {
    path: "/boardview/:announcementid",
    name: "boardview",
    // beforeEnter: onlyAuthUser,
    component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardView"),
  },
  // {
  //   path: "modify",
  //   name: "boardmodify",
  //   beforeEnter: onlyAuthUser,
  //   component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardModify"),
  // },
  // {
  //   path: "delete/:articleno",
  //   name: "boarddelete",
  //   beforeEnter: onlyAuthUser,
  //   component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardDelete"),
  // },

  
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
