import React from "react";

export default function Gallery() {
  return (
    <div class="grid grid-flow-col grid-rows-2 grid-cols-3 gap-8">
      <div class="transform scale-110 -rotate-6 w-48">
        <img src={`${process.env.PUBLIC_URL}/images/alto_ev_xev.jpg`}  alt="" loading="lazy" />
      </div>
      <div class="col-start-3 transform scale-75 rotate-6 translate-x-2 translate-y-15 w-48">
        <img src={`${process.env.PUBLIC_URL}/images/alto_ev_xev.jpg`} alt="" loading="lazy" />
      </div>
      <div class="transform scale-150 translate-y-11 w-48">
        <img src={`${process.env.PUBLIC_URL}/images/alto_ev_xev.jpg`} alt="" loading="lazy" />
      </div>
      <div class="transform translate-y-24 w-48">
        <img src={`${process.env.PUBLIC_URL}/images/alto_ev_xev.jpg`} alt="" loading="lazy" />
      </div>
      <div class="row-start-1 col-start-2 col-span-2 transform translate-x-20 translate-y-4">
        <img src={`${process.env.PUBLIC_URL}/images/alto_ev_xev.jpg`} alt="" loading="lazy" />
      </div>
    </div>
  );
}
