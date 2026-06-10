/**
 * 
 */

// --- グローバル変数定義 ---
let oldScrollPosition = window.scrollY; // スクロール位置記憶用のメモ帳（名前の衝突を完全回避）
let currentOpenedProductId = null;     // 現在モーダルで開いている商品のID

// ---  スクロールイベント（ヘッダー＆トップボタンの連動制御） ---
window.addEventListener('scroll', () => {
	const currentScrollY = window.scrollY;
	const header = document.querySelector('.site-header');
	const toTopBtn = document.getElementById('to-top-btn');

	//  ヘッダーの出し入れ処理
	if (currentScrollY < 100) {
		if (header) header.classList.remove('hide');
	} else if (currentScrollY > oldScrollPosition) {
		if (header) header.classList.add('hide'); // 下スクロールで隠す
	} else {
		if (header) header.classList.remove('hide'); // 上スクロールで出す
	}

	//  ページトップ戻りボタンの出し入れ処理（300px以上で表示）
	if (toTopBtn) {
		if (currentScrollY > 300) {
			toTopBtn.classList.add('is-show');
		} else {
			toTopBtn.classList.remove('is-show');
		}
	}

	// 次のスクロール比較のために今の位置を記憶
	oldScrollPosition = currentScrollY;
});

// ---  ページトップへ滑らかに戻る関数 ---
function scrollToTop() {
	window.scrollTo({
		top: 0,
		behavior: 'smooth'
	});
}

// ---  モーダル制御関数 ---
function openModal(memoryId) {
    //. データの存在チェック
    const data = memoryData[memoryId];
    if (!data) return;

    currentOpenedProductId = memoryId; // カート用にIDを記憶

    //  モーダル内のテキスト書き換え
    document.getElementById('modal-product-id').innerText = data.id;
    document.getElementById('modal-product-name').innerText = data.name;
    document.getElementById('modal-product-desc').innerText = data.desc;
    document.getElementById('modal-product-price').innerText = '¥' + Number(data.price).toLocaleString();

    //  360°天球画像の読み込みとセット
    const skyElement = document.getElementById('modal-sky');
    if (skyElement) {
        if (data.sphere) {
            const imagePath = window.location.origin + '/ShoppingSite/img/3d/' + data.sphere;
            skyElement.setAttribute('src', imagePath);
        } else {
            skyElement.setAttribute('src', '');
        }
    }

    //  モーダルを表示（JSP・CSSとクラス名を一到させる）
    const modal = document.getElementById('product-modal');
    if (modal) {
        modal.classList.add('is-open');
    }
}

// マウスが押された位置を記憶する変数
let startX = 0;
let startY = 0;

//  背景（モーダル）の「画面が押された瞬間」の記録
function handleModalMouseDown(event) {
    startX = event.clientX;
    startY = event.clientY;
}

//  背景（モーダル）の「画面からマウスが離れた瞬間」の判定
function handleModalMouseUp(event) {
    const endX = event.clientX;
    const endY = event.clientY;

    // クリックした位置と離した位置の距離を計算（5ピクセル未満のズレなら「純粋なクリック」とみなす）
    const distance = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

    if (distance < 5) {
        // マウスがほとんど動いていないので、純粋な背景クリックと判定して閉じる！
        closeModal();
    }
}

function closeModal() {
    const modal = document.getElementById('product-modal');
    if (modal) {
        modal.classList.remove('is-open');
    }
}

// ---  カート画面への遷移関数 ---
function addToCart() {
	if (currentOpenedProductId) {
		// サーブレットに対して商品IDをパラメータとして送信
		location.href = '../cart/CartAdd?productId=' + currentOpenedProductId;
	}
}


// ==========================================
//  インタラクティブ・マルチBlobシステム
// ==========================================

let canvas, ctx;
let blobs = []; // 3つのBlobを管理する配列

class Blob {
  constructor() {
    this.points = [];
  }
  
  init() {
    for(let i = 0; i < this.numPoints; i++) {
      let point = new Point(this.divisional * ( i + 1 ), this);
      this.push(point);
    }
  }
  
  render(targetCtx) {
    let pointsArray = this.points;
    let points = this.numPoints;
    let center = this.center;
    
    pointsArray[0].solveWith(pointsArray[points-1], pointsArray[1]);

    let p0 = pointsArray[points-1].position;
    let p1 = pointsArray[0].position;
    let _p2 = p1;

    targetCtx.beginPath();
    targetCtx.moveTo(center.x, center.y);
    targetCtx.moveTo( (p0.x + p1.x) / 2, (p0.y + p1.y) / 2 );

    for(let i = 1; i < points; i++) {
      pointsArray[i].solveWith(pointsArray[i-1], pointsArray[i+1] || pointsArray[0]);

      let p2 = pointsArray[i].position;
      var xc = (p1.x + p2.x) / 2;
      var yc = (p1.y + p2.y) / 2;
      targetCtx.quadraticCurveTo(p1.x, p1.y, xc, yc);
      p1 = p2;
    }

    var xc = (p1.x + _p2.x) / 2;
    var yc = (p1.y + _p2.y) / 2;
    targetCtx.quadraticCurveTo(p1.x, p1.y, xc, yc);

    targetCtx.fillStyle = this.color;
    targetCtx.fill();
  }
  
  push(item) {
    if(item instanceof Point) {
      this.points.push(item);
    }
  }
  
  set color(value) { this._color = value; }
  get color() { return this._color || '#ECF0F3'; }
  
  set numPoints(value) { if(value > 2) { this._points = value; } }
  get numPoints() { return this._points || 32; }
  
  set radius(value) { if(value > 0) { this._radius = value; } }
  get radius() { return this._radius || 200; } // デフォルトサイズを調整
  
  set position(value) { if(typeof value == 'object' && value.x && value.y) { this._position = value; } }
  get position() { return this._position || { x: 0.5, y: 0.5 }; }
  
  get divisional() { return Math.PI * 2 / this.numPoints; }
  get center() { return { x: canvas.width * this.position.x, y: canvas.height * this.position.y }; }
}

class Point {
  constructor(azimuth, parent) {
    this.parent = parent;
    this.azimuth = Math.PI - azimuth;
    this._components = { x: Math.cos(this.azimuth), y: Math.sin(this.azimuth) };
    this.acceleration = -0.3 + Math.random() * 0.6;
  }
  
  solveWith(leftPoint, rightPoint) {
    this.acceleration = (-0.3 * this.radialEffect + ( leftPoint.radialEffect - this.radialEffect ) + ( rightPoint.radialEffect - this.radialEffect )) * this.elasticity - this.speed * this.friction;
  }
  
  set acceleration(value) {
    if(typeof value == 'number') {
      this._acceleration = value;
      this.speed += this._acceleration * 2;
    }
  }
  get acceleration() { return this._acceleration || 0; }
  
  set speed(value) {
    if(typeof value == 'number') {
      this._speed = value;
      this.radialEffect += this._speed * 5;
    }
  }
  get speed() { return this._speed || 0; }
  
  set radialEffect(value) { if(typeof value == 'number') { this._radialEffect = value; } }
  get radialEffect() { return this._radialEffect || 0; }
  
  get position() {
    return { 
      x: this.parent.center.x + this.components.x * (this.parent.radius + this.radialEffect), 
      y: this.parent.center.y + this.components.y * (this.parent.radius + this.radialEffect) 
    }
  }
  get components() { return this._components; }
  get elasticity() { return 0.001; }
  get friction() { return 0.03; }
}

//  複数Blobの初期化と一元アニメーションループ
function initBlobs() {
  canvas = document.getElementById('blob-canvas');
  if (!canvas) return;
  ctx = canvas.getContext('2d');
  canvas.style.touchAction = 'none';

  let resize = function() {
    // コンテナの大きさにジャストフィットさせる
    canvas.width = canvas.parentElement.clientWidth;
    canvas.height = canvas.parentElement.clientHeight;
  }
  window.addEventListener('resize', resize);
  resize();

  //  大きさ・位置・色を変えて3つのBlobを生成
  const config = [
    { radius: 400, pos: { x: 0.85, y: 0.85 }, color: '#E1E7EC' }, // 左側（大）
    { radius: 240,  pos: { x: 0.35, y: 0.15 }, color: '#D6DEE6' }, // 右側（中）
    { radius: 160,  pos: { x: 0.10, y: 0.95 }, color: '#D6DEE6' }  // 中央下（小）
  ];

  blobs = config.map(cfg => {
    let b = new Blob();
    b.radius = cfg.radius;
    b.position = cfg.pos;
    b.color = cfg.color;
    b.init();
    return b;
  });

  //  描画ループを1つに統合（お互いに消し合わないようにする）
  function mainLoop() {
    ctx.clearRect(0, 0, canvas.width, canvas.height); // 画用紙を1回だけ全消去
    blobs.forEach(b => b.render(ctx));               // 3つのBlobを順番に描画
    requestAnimationFrame(mainLoop);
  }
  mainLoop();

  //  マウスイベントの中で3つのBlobすべてに対して個別判定を回す
  let oldMousePoint = { x: 0, y: 0 };
  let hoverStates = [false, false, false];

  let mouseMove = function(e) {
    // canvas要素の画面上の絶対位置を取得して、マウス座標のズレを補正
    const rect = canvas.getBoundingClientRect();
    const mouseX = e.clientX - rect.left;
    const mouseY = e.clientY - rect.top;

    blobs.forEach((b, index) => {
      let pos = b.center;
      let diff = { x: mouseX - pos.x, y: mouseY - pos.y };
      let dist = Math.sqrt((diff.x * diff.x) + (diff.y * diff.y));
      let angle = null;
      
      b.mousePos = { x: pos.x - mouseX, y: pos.y - mouseY };
      
      if(dist < b.radius && hoverStates[index] === false) {
        let vector = { x: mouseX - pos.x, y: mouseY - pos.y };
        angle = Math.atan2(vector.y, vector.x);
        hoverStates[index] = true;
      } else if(dist > b.radius && hoverStates[index] === true){ 
        let vector = { x: mouseX - pos.x, y: mouseY - pos.y };
        angle = Math.atan2(vector.y, vector.x);
        hoverStates[index] = false;
      }
      
      if(typeof angle == 'number') {
        let nearestPoint = null;
        let distanceFromPoint = 100;
        
        b.points.forEach((point)=> {
          if(Math.abs(angle - point.azimuth) < distanceFromPoint) {
            nearestPoint = point;
            distanceFromPoint = Math.abs(angle - point.azimuth);
          }
        });
        
        if(nearestPoint) {
          let strength = { x: oldMousePoint.x - mouseX, y: oldMousePoint.y - mouseY };
          strength = Math.sqrt((strength.x * strength.x) + (strength.y * strength.y)) * 10;
          if(strength > 100) strength = 100;
          nearestPoint.acceleration = strength / 100 * (hoverStates[index] ? -1 : 1);
        }
      }
    });
    
    oldMousePoint.x = mouseX;
    oldMousePoint.y = mouseY;
  }
  
  canvas.addEventListener('pointermove', mouseMove);
}

// 起動
window.addEventListener('load', initBlobs);