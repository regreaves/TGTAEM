/*

Part of the current code was adapted from W. S. Woh. The code here is sloppy and likely to change drastically as the project evolves.

https://code-boxx.com/simple-pure-javascript-typewriter-effect/

*/

var tw = {
  /* [THE SETTINGS] */
  container: "twrap", // ID of typewriter container
  text: [ // Blocks of text to show
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sodales efficitur elit eu gravida. Duis vel augue justo. Nulla sed magna laoreet, fringilla diam et, sagittis nulla. Suspendisse commodo mattis tellus, id ultrices risus consequat eu. In varius maximus mauris. Vestibulum placerat nulla ut urna interdum porta. Curabitur nec felis leo. Pellentesque vehicula nulla diam. Integer et finibus ex. Duis elementum turpis dolor, sit amet placerat velit tempor et. Nam varius mi id tortor aliquam tempor. Nunc magna mauris, scelerisque quis turpis in, luctus sodales quam. Donec commodo massa massa, id pharetra odio pretium ut. Proin lectus ex, dictum quis placerat eget, sagittis a urna.<br><br>Suspendisse turpis lectus, convallis id faucibus sit amet, sagittis vel velit. Curabitur dapibus, sapien a fringilla viverra, neque nisi ultricies enim, a sodales magna justo ac urna. Curabitur vitae erat nibh. Nunc rutrum semper dui sit amet fringilla. Nam vehicula risus congue lectus aliquet porttitor. Suspendisse auctor consectetur egestas. Mauris elit nulla, condimentum vel venenatis vitae, fermentum ut ligula. Vestibulum luctus ex orci. Ut lectus tortor, accumsan non quam sodales, efficitur faucibus odio. Sed sollicitudin risus sapien, ac suscipit metus molestie vitae. Sed at nunc vitae sem malesuada condimentum quis id augue. Duis placerat dictum lacus, a aliquet arcu sagittis gravida. Aliquam erat volutpat. Morbi convallis, augue quis convallis tristique, nisl metus tempor sem, nec porta orci nulla vel est. Quisque iaculis varius metus quis egestas.<br><br>Etiam nec felis vel orci pellentesque vulputate et quis massa. Integer leo nisl, posuere eget laoreet a, interdum id leo. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec a cursus felis. Cras vestibulum mi lobortis turpis finibus dictum. Integer vel vestibulum elit. Quisque a felis ac lectus iaculis malesuada vel eget turpis. Phasellus sodales eget tortor pretium venenatis. Praesent varius congue ante, bibendum pretium lectus venenatis a. Integer sollicitudin et ante non lobortis. Vivamus id purus pulvinar lectus auctor fringilla ut interdum sem.<br><br>Integer ut justo dolor. Aliquam non dolor id eros molestie vestibulum. In hac habitasse platea dictumst. Aenean ante lacus, condimentum sed purus ut, vulputate fringilla odio. Nunc laoreet, sapien in euismod suscipit, augue tortor fermentum nisl, eu ultrices metus orci a urna. Morbi blandit viverra ultricies. Pellentesque feugiat, enim eu suscipit faucibus, nisl sapien aliquam felis, in viverra mi arcu eget enim. Integer scelerisque tortor quam. Fusce ultrices ante vel porta eleifend.<br><br>Proin scelerisque vehicula ex id molestie. Aliquam erat volutpat. Integer aliquet eget odio eu facilisis. Praesent suscipit metus quis ex dictum euismod. Donec nec tortor massa. Pellentesque quis luctus libero, bibendum mollis quam. Integer at elit sed lectus eleifend mattis. Integer tempor ipsum at arcu facilisis tristique."
  ], 
  delay: 2, // Delay in between each character
  blockDelay: 2000, // Delay in between each block of text

  /* [THE MECHANICS] */
  timer: null, // Used to hold the timer
  pointer: 0, // Current text position
  block: 0, // Current block of text
  draw : function () {
  // tw.draw() : typewriter effect

    // Draw next character
    tw.pointer++;
    tw.container.innerHTML = tw.text[tw.block].substring(0, tw.pointer);
    if (tw.pointer < tw.text[tw.block].length) {
      tw.timer = setTimeout(tw.draw, tw.delay);
      window.scrollBy(0, window.innerHeight);
    }

    // End of block - Draw next block or cycle back to first?
    else {
      tw.block++;
      if (tw.text[tw.block] == undefined) {
        tw.block = 0;
      }
      tw.timer = setTimeout(tw.reset, tw.blockDelay);
    }
  },

  reset : function () {
  // tw.reset : Onto the next block of text

    tw.pointer = 0;
    tw.container.innerHTML = "";
    tw.timer = setTimeout(tw.draw, tw.delay);
  }
};

window.addEventListener("load", function(){
  tw.container = document.getElementById(tw.container);
  tw.draw();
});
