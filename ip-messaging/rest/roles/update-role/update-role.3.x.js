// To set up environmental variables, see http://twil.io/secure
const accountSid = process.env.TWILIO_ACCOUNT_SID;
const authToken = process.env.TWILIO_AUTH_TOKEN;
const Twilio = require('twilio').Twilio;

const client = new Twilio(accountSid, authToken);
const service = client.chat.services('ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX');

service
  .roles('RLXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
  .fetch()
  .then(role => {
    const newPermissions = ['sendMediaMessage'].concat(role.permissions || []);
    return role.update({ permission: newPermissions });
  })
  .then(response => {
    console.log(response);
  })
  .catch(error => {
    console.log(error);
  });
