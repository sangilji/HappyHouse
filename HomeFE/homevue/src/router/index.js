import Vue from "vue";
import VueRouter from "vue-router";
import HomeMainView from "../views/HomeMainView.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: HomeMainView,
    
  },
  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardMain"),
      },
      {
        path: "write",
        name: "boardwrite",
        // beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
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
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
