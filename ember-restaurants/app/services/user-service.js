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
  },

  addUser: function(addInfo){
    return this.get('ajaxService').request('/admin/addUser',{
      method: 'POST',
      data: JSON.stringify({
        firstName: addInfo.firstName,
        lastName: addInfo.lastName,
        email: addInfo.email,
        phone: addInfo.phone,
        country: addInfo.country,
        city: addInfo.city,
        password: addInfo.password
      })
    });
  },

  editUser: function(user){
    return this.get('ajaxService').request('/admin/editUser',{
      method: 'POST',
      data: JSON.stringify({
        id: user.id,
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        phone: user.phone,
        country: user.country,
        city: user.city,
        password: user.password
      })
    });
  },

  getUserDetails: function(_id){
    return this.get('ajaxService').request('/admin/getUserDetails',{
      method: 'POST',
      data: JSON.stringify({
        id: _id
      })
    });
  },

  promoteUser: function(_id){
    return this.get('ajaxService').request('/admin/promoteUser',{
      method: 'POST',
      data: JSON.stringify({
        id: _id
      })
    });
  },

  getUsersByFilter: function(filter){
    return this.get('ajaxService').request('/admin/getFilteredUsers',{
      method: 'POST',
      data: JSON.stringify({
        itemsPerPage: filter.itemsPerPage,
        pageNumber: filter.pageNumber,
        searchText: filter.searchText
      })
    });
  },

  deleteUser: function(_id){
    return this.get('ajaxService').request('/admin/deleteUser',{
      method: 'POST',
      data: JSON.stringify({
        id: _id
      })
    });
  }





});
