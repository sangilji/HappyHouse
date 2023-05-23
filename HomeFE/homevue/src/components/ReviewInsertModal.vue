<template>
  <div class="modal" tabindex="-1" id="reviewInsertModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <i class="bi bi-card-text ms-2 me-2" style="font-size: 1.5rem"></i>
          <h5 class="modal-title">리뷰 등록</h5>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div>
            <div class="border-bottom d-flex pb-2">
                
              <h5 v-if="currentIndex!=null && currentIndex>=0">{{ houseList[currentIndex].apartmentName }}</h5>
            </div>
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">거주타입</div>
              <div>
                <div
                  class="d-flex justify-content-center"
                >
                  <label
                    class="small-gray-font"
                    style="font-weight: bolder;"
                    ><input
                      type="radio"
                      v-model="type"
                      name="type"
                      value="전세"
                    />전세</label
                  >
                  <label class="small-gray-font" style="font-weight: bolder"
                    ><input
                      type="radio"
                      name="type"
                      v-model="type"
                      value="월세"
                    />월세</label
                  >
                  <label class="small-gray-font" style="font-weight: bolder"
                    ><input
                      type="radio"
                      name="type"
                      v-model="type"
                      value="매매"
                    />매매</label
                  >
                </div>
              </div>
            </div>
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">입주년도</div>
              <div><date-picker value-type="YYYY" v-model="year" type="year">
            </date-picker></div>
            </div>
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">거주 층</div>
              <input type="text" v-model="floor">
            </div>
            <div class="border-bottom d-flex py-2 text-danger">
              <div class="w-25">별점</div>
              <div>
                <StarRating
                  v-model="stars"
                  :increment="increment"
                  :show-rating="false"
                  :rounded-corners="true"
                  :star-size="20"
                  :star-points="[
                    23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36, 34,
                    46, 19, 31, 17,
                  ]"
                ></StarRating>
              </div>
            </div>
          </div>
          <div class="pt-2 form-group">
            <label class="form-label">종합의견</label>
            <textarea
              @keyup.enter="insertReview"
              v-model="content"
              class="form-control"
              rows="4"
              placeholder="사용자에게 도움이 되는 의견을 남겨주세요~!"
              aria-label="With textarea"
            ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button
            @click="closeModal"
            class="btn btn-sm btn-secondary btn-outline"
            data-dismiss="modal"
            type="button"
          >
            취소
          </button>
          <button
            @click="insertReview"
            class="btn btn-sm btn-primary btn-outline"
            data-dismiss="modal"
            type="button"
          >
            등록
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import StarRating from "vue-star-rating";
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
const dealInfoStore = "dealInfoStore";
const reviewStore = "reviewStore";

export default {
  name: "ReviewInsertModal",
  data() {
    return {
      stars: 0,
      content: "",
      increment: 0.5,
      type: "전세",
      year:2023,
      floor:"",
      apartmentName:"",
    };
  },
  props:['memberId'],
  components: {
    StarRating,
    DatePicker
  },
  methods: {
    ...mapActions(reviewStore,["newReview"]),
    async insertReview() {
      const review = {
        aptCode : this.houseList[this.currentIndex].aptCode,
        memberId : this.memberId,
        stars : this.stars,
        content : this.content,
        residenceType : this.type,
        residenceYear : this.year,
        residenceFloor : this.floor
      }
      console.log(review);
      await this.newReview(review);
      this.$emit('parent-modal-close',review.aptCode)
      this.initModal();
    //   http.post('/house/review', {
    //     userSeq: 2,
    //     houseNo,
    //     trafficScore: this.trafficScore,
    //     surroundingScore: this.surroundingScore,
    //     recommendScore: this.recommendScore,
    //     livingScore: this.livingScore,
    //     content: this.content,
    //   })
    //     .then(() => {
    //       this.$swal('리뷰가 등록되었습니다.', '참여해 주셔서 감사합니다.', { icon: 'success' })
    //           .then(() => { this.$emit('parent-modal-close',this.houseList[this.currentIndex].aptCode);});
    //     })
    //     .catch(error => {
    //           console.log(error);
    //           this.$swal('서버에 문제가 발생하였습니다.' , { icon: 'error' });
    //     })
    },
    initModal() {
        this.type="전세";
        this.year = "";
        this.floor=""
      this.stars = 0;
      this.content = "";
    },
    closeModal() {
      // this.$emit('parent-modal-close',this.houseList[this.currentIndex].aptCode);
    },
  },
  computed: {
    
    ...mapState(dealInfoStore, ["houseList", "currentIndex"]),
  },
  mounted() {
    let $this = this;
    this.$el.addEventListener("show.bs.modal", function () {
      $this.initModal();
    });
  },
};
</script>

<style></style>
