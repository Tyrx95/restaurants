import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route'

export default AdminRoute.extend({

  categoryService: Ember.inject.service(),

  model: function(params){
    return Ember.RSVP.hash({
      category: this.get('categoryService').getCategoryDetails(params.id)
    });
  }

});
