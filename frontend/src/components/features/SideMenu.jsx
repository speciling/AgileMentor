import * as React from 'react';
import { styled } from '@mui/material/styles';
import Avatar from '@mui/material/Avatar';
import MuiDrawer, { drawerClasses } from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import Divider from '@mui/material/Divider';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import LogoutIcon from '@mui/icons-material/Logout';
import SelectProject from '../common/SelectProject';
import MenuContent from '../common/MenuContent';

const drawerWidth = 240;
const headerHeight = 76;

const Drawer = styled(MuiDrawer)({
  width: drawerWidth,
  flexShrink: 0,
  boxSizing: 'border-box',
  [`& .${drawerClasses.paper}`]: {
    width: drawerWidth,
    boxSizing: 'border-box',
    top: headerHeight,
    height: `calc(100% - ${headerHeight}px)`,
  },
});

export default function SideMenu() {
  // eslint-disable-next-line no-unused-vars
  const [user, setUser] = React.useState({
    name: 'Kim Misu',
    email: 'kimmisu@email.com',
  });

  return (
    <Drawer
      variant="permanent"
      sx={{
        [`& .${drawerClasses.paper}`]: {
          backgroundColor: 'background.paper',
        },
      }}
    >
      <Box
        sx={{
          display: 'flex',
          p: 1.5,
        }}
      >
        <SelectProject />
      </Box>
      <Divider />
      <MenuContent />
      <Stack
        direction="row"
        sx={{
          p: 2,
          gap: 1,
          alignItems: 'center',
          borderTop: '1px solid',
          borderColor: 'divider',
        }}
      >
        <Avatar
          alt={user.name}
          title={user.name}
          sx={{ width: 36, height: 36 }}
        >
          {user.name.charAt(0)}
        </Avatar>
        <Box sx={{ mr: 'auto' }}>
          <Typography variant="body2" sx={{ fontWeight: 600, lineHeight: '16px' }}>
            {user.name}
          </Typography>
          <Typography variant="caption" sx={{ color: 'text.secondary', fontSize: '0.55rem' }}>
            {user.email}
          </Typography>
        </Box>
        <Button size="small" sx={{ color: 'text.secondary', minWidth: 'auto', p: 0.5, ml: 1 }}>
          <LogoutIcon sx={{ fontSize: '20px' }} />
        </Button>
      </Stack>
    </Drawer>
  );
}
