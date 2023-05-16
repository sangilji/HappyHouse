<template>
  <div  class="about">
    <house-header></house-header>
    <house-footer></house-footer>
  </div>
</template>

<script>
// @ is an alias to /src
import HouseHeader from '@/components/HouseHeader.vue';
export default {
  name: 'HouseMainView',
  components: {
    HouseHeader,
  }
}
</script>

