import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Stack from '@mui/material/Stack';
import DashboardRoundedIcon from '@mui/icons-material/DashboardRounded';
import ViewColumnRoundedIcon from '@mui/icons-material/ViewColumnRounded';
import ShowChartRoundedIcon from '@mui/icons-material/ShowChartRounded';
import ListAltRoundedIcon from '@mui/icons-material/ListAltRounded';

const menuItems = [
  { id: 'dashboard', text: 'Dashboard', icon: () => <DashboardRoundedIcon /> },
  { id: 'kanban', text: 'Kanban Board', icon: () => <ViewColumnRoundedIcon /> },
  { id: 'burndown', text: 'Burndown Chart', icon: () => <ShowChartRoundedIcon /> },
  { id: 'backlog', text: 'Backlog', icon: () => <ListAltRoundedIcon /> },
];

export default function MenuContent() {
  const [selectedItem, setSelectedItem] = React.useState('dashboard');
  const listItemButtonStyle = {
    py: 2,
    px: 1,
  };

  return (
    <Stack sx={{ flexGrow: 1, p: 2 }}>
      <List dense>
        {menuItems.map((item) => (
          <ListItem key={item.id} disablePadding sx={{ display: 'block' }}>
            <ListItemButton
              selected={item.id === selectedItem}
              onClick={() => setSelectedItem(item.id)}
              sx={listItemButtonStyle}
            >
              <ListItemIcon>{item.icon()}</ListItemIcon>
              <ListItemText
                primary={item.text}
                primaryTypographyProps={{ fontSize: '0.9rem' }}
              />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Stack>
  );
}
