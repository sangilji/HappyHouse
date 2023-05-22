<template>
  <div>
    <div id="map" style="width: 100%; height: 82vh"></div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const dealInfoStore = "dealInfoStore";
export default {
  name: "KakaoMap",
  components: {},
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
    };
  },

  watch: {
    houseList() {
      // console.log("houseList change");
      this.positions = [];
      this.houseList.forEach((house) => {
        let obj = {};
        obj.title = house.apartmentName;
        obj.latlng = new kakao.maps.LatLng(house.lat, house.lng);
        this.positions.push(obj);
      });
      this.loadMaker();
    },
  },
  created() {},
  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  computed: {
    ...mapState(dealInfoStore, ["houseList"]),
  },
  methods: {
    ...mapMutations(dealInfoStore, ["SET_CURRENT_INDEX"]),
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012647456244, 127.03958123605),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);
      //   this.loadMaker();
    },
    // 지정한 위치에 마커 불러오기
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      // console.log("1111");
      this.deleteMarker();
      // console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          //   image: markerImage, // 마커의 이미지
        });
        this.markers.push(marker);
      });
      console.log("마커수 ::: " + this.markers.length);
      if (this.positions != 0) {
        this.addInfoWindow();
      }
      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      if (this.positions.length == 0) {
        var moveLatLon = new kakao.maps.LatLng(37.5012647456244, 127.03958123605);

        // 지도 중심을 이동 시킵니다
        this.map.setCenter(moveLatLon);
      } else {
        const bounds = this.positions.reduce(
          (bounds, position) => bounds.extend(position.latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    deleteMarker() {
      console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
        });
      }
    },
    changeCurrentIndex(index) {
      console.log(index);
      this.SET_CURRENT_INDEX(index);
    },
    addInfoWindow() {
      console.log("addiw");
      this.markers.forEach((marker, index) => {
        let item = this.houseList[index];
        let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.apartmentName}</div>`;

        let infoWindow = new kakao.maps.InfoWindow({
          content: infoContents,
        });
        kakao.maps.event.addListener(marker, "click", function () {
          $this.changeCurrentIndex(index);
        });
        let $this = this;
        kakao.maps.event.addListener(marker, "mouseover", function () {
          infoWindow.open($this.map, marker);
          // $this.curInfoWindow = infoWindow;
        });
        kakao.maps.event.addListener(marker, "mouseout", function () {
          infoWindow.close();
        });
      });
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 700px;
}
</style>
