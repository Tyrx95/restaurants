import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route';
import Filter from 'ember-restaurants/models/filter';

export default AdminRoute.extend({

  categoryService: Ember.inject.service(),

  model: function(){
    return Ember.RSVP.hash({
      searchResponse: this.get('categoryService').getCategoriesByFilter({
        pageNumber: 1,
        itemsPerPage: 14
      })
    });
  },

  actions: {

    willTransition: function() {
      var filter = Filter.create({
        pageNumber: 1,
        itemsPerPage: 14,
        searchText: ""
      });
      this.controller.set('filter', filter);
    }
  }


});
