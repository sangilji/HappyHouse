<template>
  <b-tr>
    <b-td>{{ announcementid }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardview', params: { announcementid: announcementid } }">{{ title }}</router-link>
    </b-th>
    <b-td>{{ memberid }}</b-td>
    <!-- <b-td>{{ regtime | dateFormat }}</b-td> -->
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    announcementid: Number,
    memberid: String,
    title: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
