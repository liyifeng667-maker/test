
import { JSEncrypt } from 'encryptlong'

// 密钥对生成 http://web.chacuo.net/netrsakeypair; 把下面生成的公钥、私钥换成自己生成的即可
export default {
  // 加密
  encrypt (txt, publicKey) {
    txt = encodeURIComponent(txt)
    // publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8TW+bMtywHcmMafDufj09LUylKK2j/9JyarcIwF2AJbr2MU44TP8TvIHDzEKRs2fPjcKUxtdvQlex8bsOniiaeCgSSerRZuKreizgRPpK820t0Z29BLybIhmiPGIYvEVLbqCRS4o5YnxU9nmXdKtsPec+pwrtpFsan2t3hKjpFQIDAQAB'
    const encryptor = new JSEncrypt()
    encryptor.setPublicKey(publicKey) // 设置公钥
    return encryptor.encryptLong(txt) // 对数据进行加密
  },

  // 解密
  decrypt (txt, privateKey) {
    // privateKey = 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALxNb5sy3LAdyYxp8O5+PT0tTKUoraP/0nJqtwjAXYAluvYxTjhM/xO8gcPMQpGzZ8+NwpTG129CV7Hxuw6eKJp4KBJJ6tFm4qt6LOBE+krzbS3Rnb0EvJsiGaI8Yhi8RUtuoJFLijlifFT2eZd0q2w95z6nCu2kWxqfa3eEqOkVAgMBAAECgYA50WESe2SN4yeOqLzkzfhJv44ILFxcveFFZoT69+39uhgVHgCRgvrTeY8yqpZKHJckgLedbQybSfiZV8sC6wc2GAZCjUYzkb4KMKsCnYz+ms40ADvLf0YmNCWkRJ4jjIJMUpy1AYRNXCd764LRaCeCXYF/7dB3LBQqrcXhaK9xJQJBAOK4VTWgl203oyvIaPRCyND4SenrxQUFrxeXRcK1b/gQipN9vnQx6hkpHbaQZKPj9JQk/f0XwJPGYKmk7xVbaYsCQQDUnveXsjdUcV2VeqYzWOUF/NznDInT2rpLkf2x4wEmI9+rU8gjq+5xTH9u2EeT61lST6ZBf810LOG6oz2PXQvfAkEAxBnIqiADmlZbbo1kKpJxfD6mQAzMVzG5ZuBTWLzy4k/8bOKAj6R05dpodvah9hg//ruYbQfUbH1UMqgUnBZ7TQJAfC2r5UkMz6C2S4sqbmrIt8qLKdZE2RqyG1jUloRCM4hYJbP0/AB6bmmDM7XVeCrILC6gpziBQgzEeUDFv2lUeQJAIqAovkivEAhxQ3mS4Ktj7RDlKSrugeFIZOO0CNevxMW0/fTcai7Eh9tHhjRX8k+sc5l+/e63w0E83EIdTcDXXQ=='
    const encryptor = new JSEncrypt()
    encryptor.setPrivateKey(privateKey) // 设置私钥
    return encryptor.decryptLong(txt) // 对数据进行解密
  }
}
