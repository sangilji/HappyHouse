<template>
  <div class="like-btn" @click="toggle">
    <div v-if="heart"
      ><b-icon icon="heart-fill" font-scale="2" style="color: #d63384"></b-icon
    ></div>
    <div v-else><b-icon icon="heart " font-scale="2" style="color: #d63384"></b-icon></div>
  </div>
</template>

<script>
export default {
  name: "HeartBtn",
  props: ["enabled", "index"],
  components: {},
  data() {
    return {
      heart: this.enabled,
    };
  },
  watch: {
    enabled() {
      if (this.enabled != this.heart) {
        this.heart = this.enabled;
      }
    }
  },
  created(){ console.log("created"); },
  methods: {
    async toggle() {
      // Only animate on favoriting.
      this.heart = !this.heart;
      await this.$emit("changeHeartBtn", {
        enabled: this.heart,
        index: this.index,
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
