package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// 指定のURLを開く
		goTo("http://localhost:8080/lms");

		//Titleの取得とアサーション
		assertEquals("ログイン | LMS", webDriver.getTitle());

		// 開いたページのキャプチャを取得・evidenceフォルダに保存
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		//テスト用ログインIDの入力・入力値の確認
		WebElement loginIdElement = webDriver.findElement(By.id("loginId"));
		loginIdElement.clear();
		loginIdElement.sendKeys("StudentAA01");
		assertEquals("StudentAA01", loginIdElement.getAttribute("value"), "IDで指定した要素の値が正しく入力されていること");

		//テスト用パスワードの入力・入力値の確認
		WebElement passwordElement = webDriver.findElement(By.id("password"));
		passwordElement.clear();
		passwordElement.sendKeys("Abcd1234");
		assertEquals("Abcd1234", passwordElement.getAttribute("value"), "IDで指定した要素の値が正しく入力されていること");

		//ログインボタン押下
		WebElement loginButtonElement = webDriver.findElement(By.cssSelector("input[type='submit']"));
		assertEquals("ログイン", loginButtonElement.getAttribute("value"), "CSSセレクタで狙った要素のテキストが正しいこと");
		loginButtonElement.click();

		//Titleの取得とアサーション
		assertEquals("コース詳細 | LMS", webDriver.getTitle(), "タイトルが正しいこと");

		// 開いたページのキャプチャを取得・evidenceフォルダに保存
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// 機能メニューの選択・ヘルプリンク押下
		WebElement functionMenuElement = webDriver.findElement(By.linkText("機能"));
		functionMenuElement.click();
		WebElement helpLinkClickElement = webDriver.findElement(By.linkText("ヘルプ"));
		helpLinkClickElement.click();

		//Titleの取得とアサーション
		assertEquals("ヘルプ | LMS", webDriver.getTitle(), "タイトルが正しいこと");

		// 開いたページのキャプチャを取得・evidenceフォルダに保存
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// 機能メニューの選択・ヘルプリンク押下
		WebElement functionMenuElement = webDriver.findElement(By.linkText("機能"));
		functionMenuElement.click();
		WebElement helpLinkClickElement = webDriver.findElement(By.linkText("ヘルプ"));
		helpLinkClickElement.click();

		//元タブを保持
		String originalWindow = webDriver.getWindowHandle();

		//現在のタブ数の取得
		int beforeTabs = webDriver.getWindowHandles().size();

		//よくある質問リンク選択・押下
		WebElement questionLinkElement = webDriver.findElement(By.linkText("よくある質問"));
		questionLinkElement.click();

		//タブが増えるまで待つ
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.numberOfWindowsToBe(beforeTabs + 1));

		//新しいタブに切り替え
		for (String windowHandle : webDriver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}

		//Titleの取得とアサーション
		assertEquals("よくある質問 | LMS", webDriver.getTitle(), "タイトルが正しいこと");

		// 開いたページのキャプチャを取得・evidenceフォルダに保存
		getEvidence(new Object() {
		});
	}

}
