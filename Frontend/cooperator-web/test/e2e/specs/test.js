// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {
  'step one: home' : function (browser) {
     // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL
    browser
      .url('http://localhost:8087/#')
      .assert.title('Cooperator')
      .waitForElementVisible('body', 10000)
      // .assert.elementPresent('.login-text')
      // .setValue('input[type=text][v-model=username]','c@k.com')
      // .setValue('input[type=text][v-model=password]', 'hello')
      // .click('input[type=submit]',function(result){
      //   this.assert.strictEqual(result.status, 0);
      // })
      .end();
  }
}
