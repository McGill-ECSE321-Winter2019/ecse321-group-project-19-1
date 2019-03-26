// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {
  'step one: home' : function (browser) {
     // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL
    browser
      .url(devServer)
      .assert.title('Cooperator')
      .waitForElementVisible('body', 10000)
      .assert.elementPresent('.hello')
      .end();
  },
  'step two: register': function(browser){
    browser
      .url('http://localhost:8087/#/register')
      .waitForElementVisible('#register', 10000)
      .assert.elementPresent('option[value=Select]')
      .assert.elementPresent('input[placeholder="First Name"]')
      .assert.elementPresent('input[placeholder="Last Name"]')
      .assert.elementPresent('input[type=text]')
      .assert.elementPresent('input[type=password]')

      //Successful program manager resgistartion test 
      .click('option[value="ProgramManager"]')
      .setValue('input[placeholder="First Name"]', 'Program')
      .setValue('input[placeholder="Last Name"]', 'Manager')
      .setValue('input[placeholder="Email"]', 'pm@test.com')
      .setValue('input[type=password]','test')
      .click('input[type=submit]')
      .assert.elementPresent('#successRegister')

      //Successful term instructor registration test
      .click('option[value="TermInstructor"]')
      .setValue('input[placeholder="First Name"]', 'Term')
      .setValue('input[placeholder="Last Name"]', 'Instructor')
      .setValue('input[placeholder="Email"]', 'ti@test.com')
      .setValue('input[type=password]','test')
      .click('input[type=submit]')
      .assert.elementPresent('#successRegister')

      //Unsucessful registration test
      .setValue('input[placeholder="First Name"]','')
      .click('input[type=submit]')
      .assert.elementPresent('#errorRegister')
      .end();
  },
  'step three: program manager login':function(browser){
    browser
      .url('http://localhost:8087/#/login')
      .waitForElementVisible('#login',10000)
      .assert.elementPresent('#login')
      .setValue('input[type=text]', 'pm@test.com')
      .setValue('input[type=password]','test')
      .click('input[type=submit]',function(result){
      this.assert.strictEqual(result.status, 0);
      })
      //Logged in. Following tests are navigating the different tabs
      .waitForElementVisible('.hello') //home page
      .click('#app #selectedPmStudentTab') //student
      .waitForElementVisible('h1')
      .click('#app #selectedCourseTab') //courses
      .waitForElementVisible('h1')
      .click('#app #selectedCoopTab') //coop
      .waitForElementVisible('h1')
      .click('#app #selectedLogoutTab') //logout
      .waitForElementVisible('#logout')
      .assert.elementPresent('#logoutMessage') 
      .assert.elementPresent('input[type=submit')
      .click('input[type=submit]', function(result){
        this.waitForElementVisible('.hello');
      })
      .end();
    },
  'step four: assigning a term instructor to a coop':function(browser){
    browser
      .url('http://localhost:8087/#/Coops')
      .waitForElementVisible('.page-title')
      .assert.elementPresent('input[placeholder="CoopID"]')
      .assert.elementPresent('input[placeholder="Instructor Email"]')
      .assert.elementPresent('input[type=submit]')
      .setValue('input[placeholder="CoopID"]','2' )
      .setValue('input[placeholder="Instructor Email"]','ti@test.com')
      .click('input[type=submit]')
      .assert.elementPresent('#assignedTi')
      .end();

  }
      
}

