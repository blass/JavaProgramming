import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game1Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Game1Mainのインスタンス作成
		new Game1Main();
	}
	JFrame mainwindow;
	BufferStrategy buffer;
	BufferedImage img_back, img_, img_cloud, img_jiki, img_teki, img_title;

	//雲の座標
	int[] cx = {480, 860};
	int[] cy = {0, 80};
	//コンストラクトの定義
	public Game1Main () {
		//JFrameのインスタンスの作成
		this.mainwindow = new JFrame("ゲームウィンドウ");
		//ウインドウズをクローズするときの処理
		this.mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ウィンドウの表示
		this.mainwindow.setVisible(true);

	//
			Insets in = this.mainwindow.getInsets();
			this.mainwindow.setSize(480 + in.left + in.right, 360 + in.top + in.bottom);
			//画像読み込み
			try {
				//背景画像読み込み
				this.img_back = ImageIO.read(this.getClass().getResource("s_back.png"));
				//雲の画像読み込み
				this.img_cloud = ImageIO.read(this.getClass().getResource("s_cloud.png"));
				//自機の画像読み込み
				this.img_jiki = ImageIO.read(this.getClass().getResource("s_jiki.png"));
				//敵機の読み込み
				this.img_teki = ImageIO.read(this.getClass().getResource("s_teki.png"));
				//タイトルの画像読み込み
				//this.img_title = ImageIO.read(this.getClass().getResource("s_title.png"));
			} catch (IOException e) {
				System.out.println("読み込み中にエラー");
			}catch(IllegalArgumentException iae){
				System.out.println("ファイル名が違います");
			}

			//バッファの作成
			this.mainwindow.setIgnoreRepaint(true);
			this.mainwindow.createBufferStrategy(2);
			this.buffer = this.mainwindow.getBufferStrategy();

			//タイマーの作成
			Timer t = new Timer();
			t.schedule(new GameTask(), 0, 50);

	}
		class GameTask extends TimerTask{

			@Override
			public void run() {
				//バッファの消失チェック
			if (buffer.contentsLost() == false){
				Graphics g = buffer.getDrawGraphics();
				//背景描画
				g = mainwindow.getGraphics();
				g.drawImage(img_back, 8, 29, mainwindow);
				Insets ins = mainwindow.getInsets();
				g.translate(ins.left, ins.top);
				//雲の描画で左から右に流す
				for(int i=0; i< cx.length; i++){
					cx[i] -= 2;
					if (cx[i] < -409)cx[i] = 460;
					g.drawImage(img_cloud, cx[i], cy[i], mainwindow);
				}


/*				//四角を描く
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, 100, 100);
				//丸を描く
				g.setColor(new Color(255,200,0));
				g.fillOval(150, 150, 200, 200);
				//線を描く
				g.setColor(Color.BLACK);
				g.drawLine(480, 0, 0,360);
*/

				//終了処理
				g.dispose();
				buffer.show();
			}
			else{
					while (buffer.contentsRestored() == false) {
						Graphics g = buffer.getDrawGraphics();
						g.dispose();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
					}
				}
/**				public abstract boolean drawImage(
					ImageIO img,
					int x, int y,
					int width, int height,
					Color bgColor,
					ImageObserver observer
					);
*/
			}
	}
		class GameMouseAdapter extends MouseAdapter{
			this.mainwindow.addMouseListener{}
		}
}

