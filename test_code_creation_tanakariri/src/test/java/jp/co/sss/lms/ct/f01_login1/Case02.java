package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

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
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		//テスト用ログインIDの入力・入力値の確認
		WebElement loginIdElement = webDriver.findElement(By.id("loginId"));
		loginIdElement.clear();
		loginIdElement.sendKeys("StudentBB01");
		assertEquals("StudentBB01", loginIdElement.getAttribute("value"), "IDで指定した要素の値が正しく入力されていること");

		//テスト用パスワードの入力・入力値の確認
		WebElement passwordElement = webDriver.findElement(By.id("password"));
		passwordElement.clear();
		passwordElement.sendKeys("StudentBB01");
		assertEquals("StudentBB01", passwordElement.getAttribute("value"), "IDで指定した要素の値が正しく入力されていること");

		//ログインボタン押下
		WebElement loginButtonElement = webDriver.findElement(By.cssSelector("input[type='submit']"));
		assertEquals("ログイン", loginButtonElement.getAttribute("value"), "CSSセレクタで狙った要素のテキストが正しいこと");
		loginButtonElement.click();

		// 開いたページのキャプチャを取得・evidenceフォルダに保存
		getEvidence(new Object() {
		});
	}
}
