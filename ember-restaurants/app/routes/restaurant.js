import Ember from 'ember';

export default Ember.Route.extend({

  ajax: Ember.inject.service(),
  model(params) {
     let res = this.get('ajax').request('/getRestaurantDetails', {
        method: 'POST',
        data: JSON.stringify({
          Id: params.restaurant_id
        })
      });
      console.log(res);
      return res;
  }

});
