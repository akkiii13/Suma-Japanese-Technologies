import React, { Fragment, useEffect, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import {
  Autoplay,
  EffectFade,
  Keyboard,
  Navigation,
  Pagination,
  Parallax,
} from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/parallax";
import "swiper/css/effect-fade";

export default function HomeSection() {
  return (
    <Fragment>
      <Swiper
        spaceBetween={30}
        effect={"fade"}
        parallax={false}
        autoplay={{
          delay: 1000,
          disableOnInteraction: false,
        }}
        keyboard={{
          enabled: true,
        }}
        pagination={{
          clickable: true,
        }}
        grabCursor={true}
        loop={true}
        navigation={true}
        modules={[
          Autoplay,
          EffectFade,
          Keyboard,
          Navigation,
          Pagination,
          Parallax,
        ]}
        className="mt-[-4rem] mySwiper w-full h-full"
      >
        {[
          ["alto_ev_xev.jpg", "title 1", "subtitle 1", "description 1"],
          ["celerio_ev_xev.jpg", "title 2", "subtitle 2", "description 2"],
          ["datsun-go_ev_xev.jpg", "title 3", "subtitle 3", "description 3"],
          [
            "datsun-redi-go_ev_xev.jpg",
            "title 4",
            "subtitle 4",
            "description 4",
          ],
          ["eon_ev_xev.jpg", "title 5", "subtitle 5", "description 5"],
          ["i10_ev_xev.jpg", "title 6", "subtitle 6", "description 6"],
          ["kwid_ev_xev.jpg", "title 7", "subtitle 7", "description 7"],
          ["omni_ev_xev.jpg", "title 8", "subtitle 8", "description 8"],
          ["s-presso_ev_xev.jpg", "title 9", "subtitle 9", "description 9"],
          ["santro_ev_xev.jpg", "title 10", "subtitle 10", "description 10"],
        ].map(([imageName, title, subtitle, description]) => (
          <SwiperSlide className="relative text-center font-[18px] flex justify-center items-center">
              <div>
              <img
                src={`${process.env.PUBLIC_URL}/images/${imageName}`}
                alt=""
                className="block w-full h-[100vh] object-cover"
              />
              <div className="absolute z-50">
                <div>{title}</div>
              </div>
              </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </Fragment>
  );
}
