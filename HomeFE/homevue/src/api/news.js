const axios = require("axios");
const cheerio = require("cheerio");

// HTML 코드를 가지고 오는 함수
const getHTML = async (keyword) => {
  try {
    return await axios.get("https://search.naver.com/search.naver?where=news&ie=UTF-8&query=" + encodeURI(keyword), {
      maxRedirects: 5, // 최대 리디렉션 횟수 설정
    });
  } catch (err) {
    console.log(err);
  }
};

// 파싱 함수
const parsing = async (keyword) => {
  const html = await getHTML(keyword);
  const $ = cheerio.load(html.data);
  const $titlist = $(".news_area");

  let informations = [];
  $titlist.each((idx, node) => {
    const title = $(node).find(".news_tit").text();
    const url = $(node).find(".news_tit").attr("href");
    informations.push({
      title: $(node).find(".news_tit:eq(0)").text(),
      press: $(node).find(".info_group > a").text(),
      time: $(node).find(".info_group > span").text(),
      contents: $(node).find(".dsc_wrap").text(),
      url: url,
    });
    // console.log(informations);
  });
  return informations; // 정보 반환
};

module.exports = {
  parsing,
};
