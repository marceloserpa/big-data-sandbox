var fs = require('fs');

var fs = require('fs')
var logger = fs.createWriteStream('application.log', {
  flags: 'a' 
})
logger.write('Starting process...')
for(i = 0; i < 1000; i++){
    logger.write('Current Log: ' + i + ' date: ' + new Date() + '\n');
}
logger.write('end process')
