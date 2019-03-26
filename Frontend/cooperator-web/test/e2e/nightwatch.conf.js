require('babel-register')
var config = require('../../config')

// http://nightwatchjs.org/gettingstarted#settings-file
module.exports = {
  src_folders: ['/specs'],
  //output_folder: '/reports',
  //custom_assertions_path: ['/custom-assertions'],

  selenium: {
    start_process: true,
    server_path: require('selenium-server').path,
    host: '127.0.0.1',
    port: 5555,
    cli_args: {
      'webdriver.chrome.driver': require('chromedriver').path
    }
  },

  test_settings: {
    default: {
      //launch_url: 'http://localhost',
      selenium_port: 5555,
      selenium_host: 'localhost',
      silent: true,
      output: true,
      screenshots: {
        enabled: true,
        path: 'screenshots'
      },
      desiredCapabilities:{
        browserName: 'chrome',
        javascriptEnabled: true,
        acceptSslCerts: true,
      },
      globals: {
        devServerURL: 'http://localhost:' + (process.env.PORT || config.dev.port)
      }
    },

    chrome: {
      desiredCapabilities: {
        browserName: 'chrome',
        javascriptEnabled: true,
        acceptSslCerts: true
      }
    },

    firefox: {
      desiredCapabilities: {
        browserName: 'firefox',
        javascriptEnabled: true,
        acceptSslCerts: true
      }
    }
  }
}
