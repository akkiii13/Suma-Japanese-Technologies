import SvgColor from '../../component/svg-color';

// ----------------------------------------------------------------------

const icon = (name) => (
  <SvgColor src={`/assets/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />
);

const navConfig = [
  {
    title: 'home',
    path: '/',
    icon: icon('ic_analytics'),
  },
  {
    title: 'new inquiry',
    path: '/inquiries',
    icon: icon('ic_user'),
  },
  {
    title: 'vehicle',
    path: '/vehicles',
    icon: icon('ic_cart'),
  },
  {
    title: 'login',
    path: '/log-in',
    icon: icon('ic_lock'),
  },
  // {
  //   title: 'Not found',
  //   path: '/404',
  //   icon: icon('ic_disabled'),
  // },
];

export default navConfig;
