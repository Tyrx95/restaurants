import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  getUser: function(){
    return this.get('ajaxService').request('/currentUser');
  },
  register: function(registerInfo){
    return this.get('ajaxService').request('/register',{
      method: 'POST',
      data: JSON.stringify({
        firstName: registerInfo.firstName,
        lastName: registerInfo.lastName,
        email: registerInfo.email,
        phone: registerInfo.phone,
        country: registerInfo.country,
        city: registerInfo.city,
        password: registerInfo.password
      })
    });
  }
});
