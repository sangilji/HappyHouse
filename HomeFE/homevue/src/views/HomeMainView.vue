<template>
  <div  class="about">
    <house-header></house-header>
    <!-- <house-input></house-input> -->
  </div>
</template>

<script>
// @ is an alias to /src
// import TodoInput from '../components/TodoInput.vue'
import HouseHeader from '@/components/HouseHeader.vue';
export default {
  name: 'HouseMainView',
  components: {
    // TodoInput,
    HouseHeader
  }
}
</script>

    <!-- TodoFooter -->