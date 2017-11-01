import Ember from 'ember';

export default Ember.Service.extend({
   ajaxService: Ember.inject.service(),

   allLocations: function(){
     return this.get('ajaxService').request('/getRestaurantsLocations');
   }


});
